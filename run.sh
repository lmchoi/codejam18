#!/usr/bin/env bash

javac -d target/ src/Solution.java
(cd target && exec java Solution)
