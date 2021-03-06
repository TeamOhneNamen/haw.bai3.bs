#!/bin/bash 
# 
# Thorben Schomacker
# Ferdinand Trendelenburg
# 22.02.2018

echo "Ping wird durchgefuehrt"

light_red='\e[1;91m%s\e[0m\n'                     
light_green='\e[1;92m%s\e[0m\n'                   
re='^[0-9]+$'



case $1 in
	-s) 
		if ! [[ "$2" =~ ^[0-9]+$ ]] ; then
			while true; do
			# einmaliges pingen
			ping -c 1 $2 &> /dev/null
			# wenn Ergebniss = 0
			if [ "$?" -eq 0 ]; then
  				printf "$light_green" "$2 OK"
			else
  				printf "$light_red" "$2 FAILED"
			fi
			sleep 10
			done
		else
			while true; do
			# einmaliges pingen
			ping -c 1 $3 &> /dev/null
			# wenn Ergebniss = 0
			if [ "$?" -eq 0 ]; then
  				printf "$light_green" "$3 OK"
			else
  				printf "$light_red" "$3 FAILED"
			fi
			sleep $2
			done
		fi

	;;
	-h) 
		# einmaliges pingen
		ping -c 1 $2 &> /dev/null
		# wenn Ergebniss = 0                   
		if [ "$?" -eq 0 ]; then                           
  		printf "$light_green" "$2 OK"
		else                                              
  		printf "$light_red" "$2 FAILED"     
		fi
	;;
	*) echo Falscher input ;;
esac

