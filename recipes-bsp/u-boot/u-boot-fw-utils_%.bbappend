FILESEXTRAPATHS_append := "${THISDIR}/u-boot-lpkit:${THISDIR}/${PN}:${THISDIR}/files:"

include recipes-bsp/u-boot/u-boot-lpkit.inc
SRC_URI_append = " file://fw_env.config \
                    file://default-gcc.patch"

do_install_append () {
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
}
