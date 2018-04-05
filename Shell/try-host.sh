#!/bin/bash 
# 
# Thorben Schomacker
# Ferdinand Trendelenburg
# 22.02.2018

echo "Ping wird durchgefuehrt"

light_red='\e[1;91m%s\e[0m\n'                     
light_green='\e[1;92m%s\e[0m\n'                   


case $1 in
	-s) 
		# pingen im Zeitintervall von $2 sekunden
		ping -i $2 $3 
		# wenn Ergebniss = 0
		if [ "$?" -eq 0 ]; then                           
  		printf "$light_green" "$3 OK"
		else                                              
  		printf "$light_red" "$3 FAILED"     
		fi
	;;
	-h) 
		# einmaliges pingen
		ping -c 1 $2       
		# wenn Ergebniss = 0                   
		if [ "$?" -eq 0 ]; then                           
  		printf "$light_green" "$2 OK"
		else                                              
  		printf "$light_red" "$2 FAILED"     
		fi
	;;
	*) echo Falscher input ;;
esac

