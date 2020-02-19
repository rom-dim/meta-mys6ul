# Adapted from linux-imx.inc, copyright (C) 2012, 2013 O.S. Systems Software LTDA
# Released under the MIT license (see COPYING.MIT for the terms)
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc
require linux-ldkit.inc

SRCREV = "rel_imx_4.1.15_2.1.0_ga"
SRCBRANCH="imx_4.1.15_2.0.0_ga"

SRC_URI = "git://source.codeaurora.org/external/imx/linux-imx;protocol=https;branch=${SRCBRANCH} \
	   file://defconfig \
	   file://dts/ \
	   file://wm8904.patch \
	   file://git/ \
	   "

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(mys6ul14x14)"
