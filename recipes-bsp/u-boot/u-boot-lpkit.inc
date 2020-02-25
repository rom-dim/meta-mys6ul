PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV="rel_imx_4.1.15_2.0.0_ga"

SRC_URI = "git://source.codeaurora.org/external/imx/uboot-imx;branch=nxp/imx_v2016.03_4.1.15_2.0.0_ga;protocol=https \
	   file://${MACHINE}/ \ 
	   file://${MACHINE}/Kconfig.patch \
	   file://ext4.patch \ 
	   "

do_unpack_machinefiles(){
  cp -r ${WORKDIR}/${MACHINE}/* ${S}/
}

addtask do_unpack_machinefiles after do_unpack before do_patch

