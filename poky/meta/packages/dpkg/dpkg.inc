DESCRIPTION = "Package maintenance system for Debian."
LICENSE = "GPL"
SECTION = "base"
PR = "r7"

SRC_URI = "${DEBIAN_MIRROR}/main/d/dpkg/dpkg_${PV}.tar.gz \
           file://ignore_extra_fields.patch;patch=1 \
           file://noupdalt.patch;patch=1"

S = "${WORKDIR}/dpkg-${PV}"

PARALLEL_MAKE = ""

inherit autotools gettext

DPKG_INIT_POSITION = "98"
DPKG_INIT_POSITION_slugos = "41"

pkg_postinst_dpkg () {
#!/bin/sh
if [ "x$D" != "x" ]; then
	install -d $D/${sysconfdir}/rcS.d
	# this happens at S98 where our good 'ole packages script used to run
	echo -e "#!/bin/sh
	dpkg --configure -a
	rm -f /${sysconfdir}/rcS.d/S${DPKG_INIT_POSITION}configure
" > ${IMAGE_ROOTFS}/${sysconfdir}/rcS.d/S${DPKG_INIT_POSITION}configure
	chmod 0755 $D/${sysconfdir}/rcS.d/S${DPKG_INIT_POSITION}configure
fi
}

do_configure () {
    echo >> m4/compiler.m4
    autotools_do_configure
}
