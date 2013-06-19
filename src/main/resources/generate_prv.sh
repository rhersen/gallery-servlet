for im in *.JPG
do convert $im -resize 256x128\! ../prv/$im
done
