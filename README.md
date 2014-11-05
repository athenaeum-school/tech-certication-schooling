tech-certication-schooling
==========================

Assumes: mac os x port

$ chown -R _mysql:_mysql /opt/local/lib/mariadb
$ cat /opt/local/etc/mariadb/macports-default.cnf

Needed to comment out the skip-networking, as it makes mariadb stop listening on port 3306

$ sudo port load MariaDB-server
