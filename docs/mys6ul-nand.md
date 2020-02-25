#NAND Layout

-------------------- 0x0000000
| U-boot                5MiB
-------------------- 0x0500000
| U-boot (env)          1MiB
-------------------- 0x0600000
| Kernel                8MiB
-------------------- 0x0e00000
| DTB                   2MiB
-------------------- 0x1000000
| ubi
|  rootfs (squashfs)    64MiB ?
-------------------- TBD 

##TODO
 Vision

-------------------- 0x0000000
| SPL                
-------------------- 
| U-boot + env       
-------------------- 
| ubi (system A)
| ------------------- 
| FIT Image
| ------------------- 
| rootfs (squashfs)   
---------------------
| ubi (system B)
| ------------------- 
| FIT Image
| ------------------- 
| rootfs (squashfs)   
---------------------
| ubi - Backup
| ------------------- 
| FIT Image 
| ------------------- 
| rootfs (squashfs)   
---------------------

#Flashing

##TODO
Description for all steps form linux
Description for all steps form U-Boot

##U-Boot (Linux)
Use kobs tool from linux (native)

mount -t debugfs nodev /sys/kernel/debug
kobs-ng init -x -v -w --chip_0_device_path=/dev/mtd0 u-boot.imx-nand

##Kernel & DTB from U-boot (U-Boot)

We need normal zImage (without initramfs)

nand erase 0x600000 0x800000
ext4load mmc 0:1 ${loadaddr} zImage
nand write ${loadaddr} 0x600000 0x800000

ext4load mmc 0:1 ${loadaddr} mys6ul.dtb
nand write ${loadaddr} 0xe00000 0x200000


##RootFs

Use ubi layer to manage bad block on nand. 
Link : https://bootlin.com/blog/creating-flashing-ubi-ubifs-images/

Create ubi device on top of MTD, write squashfs to new device and mark them as block device. Ready

# ubiformat /dev/mtdX
# ubiattach -p /dev/mtdX
# ubimkvol /dev/ubi0 -N rootfs -s 64MiB
# ubiupdatevol /dev/ubi0_0 rootfs.squashfs
# ubiblock --create /dev/ubi0_0
# mount -t squashfs -o ro /dev/ubiblock0_0  /tmp/


#Configure U-Boot

##NAND Layout
setenv mtdids nand0=gpmi-nand
setenv mtdparts mtdparts=gpmi-nand:5m(boot),1m(env),8m(kernel),2m(dtb),64m(rootfs),-(userdata)
setenv fixbootargs 'setenv bootargs console=${console},${baudrate} ${mtdparts} ubi.mtd=rootfs ubi.block=0,0 root=/dev/ubiblock0_0 rootfstype=squashfs quiet'

##Loading
load_dtb=nand read ${fdt_addr} 0xe00000 0x200000
load_kernel=nand read ${loadaddr} 0x600000 0x800000
boot_kernel=run load_dtb; run load_kernel; run fixbootargs; bootz ${loadaddr} - ${fdt_addr}

##Booting
run boot_kernel

