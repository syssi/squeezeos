SECTION = "x11/utils"
DEPENDS = "intltool-native virtual/libintl glib-2.0 dbus dbus-glib libxml2 popt gtk-doc-native"
DESCRIPTION = "Settings daemon using DBUS for communication."
LICENSE = "GPL"
PROVIDES = "gconf"
RPROVIDES_${PN} = "gconf"
RPROVIDES_${PN}-dev = "gconf-dev"

PV = "2.16.0+svnr${SRCREV}"

SRC_URI = "svn://developer.imendio.com/svn/gconf-dbus;module=trunk;proto=http"

inherit pkgconfig autotools 
S = "${WORKDIR}/trunk"

PARALLEL_MAKE = ""

FILES_${PN} = "${libdir}/GConf-dbus/2/*.so ${libdir}/dbus-1.0 ${sysconfdir} ${datadir}/dbus* ${libdir}/*.so.* ${bindir}/* ${libexecdir}/*"
FILES_${PN}-dbg += " ${libdir}/GConf-dbus/2/.debug"

EXTRA_OECONF = "--disable-gtk-doc --disable-gtk --enable-shared --disable-static --enable-debug=yes"


do_configure_prepend() {
        touch gtk-doc.make
}

do_stage() {
        autotools_stage_all
        install -m 0644 gconf-2.m4 ${STAGING_DATADIR}/aclocal/gconf-2.m4
}
