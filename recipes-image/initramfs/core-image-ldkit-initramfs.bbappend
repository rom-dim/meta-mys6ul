PACKAGE_INSTALL_append = " hd44780 rtl8723bu"

do_install_mnt(){
 install -d ${IMAGE_ROOTFS}/mnt/user
 install -d ${IMAGE_ROOTFS}/mnt/rootfs
}

ROOTFS_POSTPROCESS_COMMAND += " do_install_mnt "
