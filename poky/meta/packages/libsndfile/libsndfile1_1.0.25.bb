DESCRIPTION = "An audio format Conversion library"
HOMEPAGE = "http://www.mega-nerd.com/libsndfile"
AUTHOR = "Erik de Castro Lopo"
DEPENDS = "sqlite3"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1"
PR = "r1"

SRC_URI = "http://www.mega-nerd.com/libsndfile/files/libsndfile-${PV}.tar.gz file://automake-silent-rules.patch;patch=1"

SRC_URI[md5sum] = "e2b7bb637e01022c7d20f95f9c3990a2"
SRC_URI[sha256sum] = "59016dbd326abe7e2366ded5c344c853829bebfd1702ef26a07ef662d6aa4882"

LIC_FILES_CHKSUM = "file://COPYING;md5=e77fe93202736b47c07035910f47974a"

S = "${WORKDIR}/libsndfile-${PV}"

#PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)}"
#PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"

EXTRA_OECONF = "--disable-external-libs"

#inherit autotools lib_package pkgconfig
inherit autotools_stage pkgconfig

do_configure_prepend_arm() {
	export ac_cv_sys_largefile_source=1
	export ac_cv_sys_file_offset_bits=64
	ac_cv_sizeof_off_t=8
}

#do_stage() {
#        autotools_stage_all
#}
