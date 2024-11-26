#!/bin/zsh




mkdir .sharing
rm -rf .sharing/archiveBuffer
rm -rf .sharing/archiveWikiBuffer


git clone --depth 1 https://github.com/ChippyPlus/micro-vm.wiki.git .sharing/archiveWikiBuffer
git clone --depth 1 https://github.com/ChippyPlus/micro-vm.git .sharing/archiveBuffer

rm -rf .sharing/archiveWikiBuffer/.git
rm -rf .sharing/archiveBuffer/.git

find .sharing/archiveBuffer > .sharing/archiveOutBuffer.txt
find .sharing/archiveWikiBuffer >> .sharing/archiveOutBuffer.txt
files=""

while IFS= read -r i; do
    i=${i%$'\n'}

    if [[ -d "$i" || "$i" =~ ^.sharing/archiveBuffer/gradle ]]; then
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
done <  .sharing/archiveOutBuffer.txt



echo "$files" > share.txt