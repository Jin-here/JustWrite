#!/bin/bash
NDK=D:/Android/sdk/ndk-bundle
PLATFORM=$NDK/platforms/android-18/arch-mips/
TOOLCHAIN=$NDK/toolchains/mipsel-linux-android-4.9/prebuilt/windows-x86_64
PREFIX=./android/mips

function build_one
{
  ./configure \
  --prefix=$PREFIX \
  --enable-static \
  --enable-pic \
  --host=mipsel-linux \
  --cross-prefix=$TOOLCHAIN/bin/mipsel-linux-android- \
  --sysroot=$PLATFORM \
  --disable-asm

  make clean
  make
  make install
}

build_one

echo Android MIPS builds finished
