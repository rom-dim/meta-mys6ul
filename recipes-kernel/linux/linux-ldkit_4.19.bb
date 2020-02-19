# Adapted from linux-imx.inc, copyright (C) 2012, 2013 O.S. Systems Software LTDA
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux kernel for RUS brighttree board"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel 
require recipes-kernel/linux/linux-dtb.inc
require linux-ldkit.inc

SRCREV = "rel_imx_4.19.35_1.1.0"
SRCBRANCH = "imx_4.19.35_1.1.0"
SRC_URI = "git://source.codeaurora.org/external/imx/linux-imx;protocol=https;branch=${SRCBRANCH} \
	   file://defconfig \
	   file://dts/ \
	   "

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(mys6ul14x14)"
