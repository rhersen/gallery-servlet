for im in *
do convert $im -resize 256x128\! ../256/$im
done
