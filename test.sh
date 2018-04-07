#!/usr/bin/env bash

javac -d target/ src/Solution.java
(cd target && python testing_tool.py java Solution)
