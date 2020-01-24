# Copyright (C) 2013-2015 Freescale Semiconductor

DESCRIPTION = "U-Boot provided by Freescale with focus on  i.MX reference boards."

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "u-boot"

require recipes-bsp/u-boot/u-boot.inc
include recipes-bsp/u-boot/u-boot-lpkit.inc


S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(mys6ul14x14)"
