#!/bin/zsh

rm -rf .archiveBuffer
git clone --depth 1 https://github.com/ChippyPlus/micro-vm.git .archiveBuffer

find .archiveBuffer > .archiveOutBuffer

files=""

while IFS= read -r i; do
    i=${i%$'\n'}

    if [[ -d "$i" || "$i" =~ ^.archiveBuffer/gradle ]]; then
        continue
    fi

    ext=""
    case "$i" in
        *.kt) ext="kotlin" ;;
        *.md) ext="markdown" ;;
        *.sh) ext="shell" ;;
        *.json) ext="json" ;;
        *.kar) ext="custom assembly" ;;
    esac

    echo $i
    if [[ -n "$ext" ]]; then
        files+=$'\nfilePath '$i$'\n```'$ext$'\n'$(cat "$i")$'\n-------------------------------\n```'
    fi
done <  .archiveOutBuffer



echo "$files" > share.txt