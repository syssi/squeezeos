require pulseaudio.inc

PR = "r12"

DEPENDS += "libjson gdbm speex"
# libxml-parser-perl-native"

inherit gettext 
#perlnative

SRC_URI = "http://freedesktop.org/software/pulseaudio/releases/pulseaudio-${PV}.tar.gz \
  file://autoconf-silent-rules-fix.patch;patch=1 \
  file://manipulate-required-alsa-version.patch;patch=1 \
  file://volatiles.04_pulse \
"

SRC_URI[md5sum] = "86912af7fd4f8aa67f83182c135b2a5c"
SRC_URI[sha256sum] = "d89520237ae775cb6f7698d40d66b848cd98fd6b46880534e15282630db7e34d"

do_configure() {
        oe_runconf
}


do_compile_prepend() {
    cd ${S}
    mkdir -p ${S}/libltdl
    cp ${STAGING_LIBDIR}/libltdl* ${S}/libltdl
}

