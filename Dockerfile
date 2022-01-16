FROM ghcr.io/graalvm/graalvm-ce:latest

RUN microdnf install zip
RUN microdnf install patch

RUN gu install python
RUN gu install native-image
RUN gu install llvm-toolchain

RUN graalpython -m ginstall install numpy
WORKDIR /app
COPY heart.csv /
COPY heartAnalysis.py /
COPY HeartAnalysisJava.java /

RUN graalpython heartAnalysis.py
RUN export GRAAL_PYTHONHOME=$GRAALVM_HOME/languages/python
RUN native-image -language:python -Dorg.graalvm.launcher.relative.python.home=$GRAALVM_HOME/languages/python -Dorg.graalvm.launcher.relative.llvm.home=$GRAALVM_HOME/languages/llvm HeartAnalysisJava



