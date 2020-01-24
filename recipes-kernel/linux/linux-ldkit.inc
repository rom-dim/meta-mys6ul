DEPENDS += "lzop-native bc-native"

do_unpack_dtb(){
  if [ -d "${WORKDIR}/dts" ] 
  then 
    cp -r ${WORKDIR}/dts/* ${S}/arch/arm/boot/dts/
  fi
}

addtask do_unpack_dtb after do_unpack before do_patch

# This part of kernel_deploy will copy existing dtb file 
# defined into KERNEL_DEFAULT_DEVICETREE to deploy folder as zImage-${MACHINE}.dtb
# and create symlinc, orginal code see at poky/meta/recipes-kernel/linux/linux-dtb.inc
do_deploy_append() {
	DTB_NAME=`echo ${type}"-"${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${MACHINE}/g"`
	DTB_SYMLINK_NAME=`echo ${type}"-"${KERNEL_IMAGE_SYMLINK_NAME} | sed "s/${MACHINE}/${MACHINE}/g"`
	install -m 0644 ${B}/arch/${ARCH}/boot/dts/${KERNEL_DEFAULT_DEVICETREE} ${DEPLOYDIR}/${DTB_NAME}.dtb
	ln -sf ${DTB_NAME}.dtb ${DEPLOYDIR}/${DTB_SYMLINK_NAME}.dtb
}

# Modify defconfig udoo neo board need PFUZE100.
do_configure_prepend_udooneo () {
    config="${WORKDIR}/defconfig"

    sed -i "/CONFIG_REGULATOR_PFUZE100[ =]/d" $config
    echo "CONFIG_REGULATOR_PFUZE100=y" >> $config
}
