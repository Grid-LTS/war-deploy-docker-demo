FROM jenkinsci/slave

LABEL maintainer="gerd.friemel@gmail.com"

#image is based on DEBIAN stretch
ENV compose_vers=1.21.2
ENV maven_vers=3.5.3
USER root

RUN apt-get update -y
RUN apt-get install -y \
         apt-transport-https \
         ca-certificates \
         curl \
         gnupg2 \
         software-properties-common \
         vim
# Install Docker
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN apt-key fingerprint 0EBFCD88

RUN echo "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get -y update && \
    apt-get install -y docker-ce

# install python, pip and docker-compose

RUN apt-get install -y python3-pip
RUN pip3 install docker
RUN curl -L https://github.com/docker/compose/releases/download/${compose_vers}/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

RUN docker-compose --version

ENV user=jenkins
ENV group=jenkins

RUN usermod -aG docker ${user}
# remove root password

# install maven
RUN cd /opt && \
    wget http://ftp.fau.de/apache/maven/maven-3/${maven_vers}/binaries/apache-maven-${maven_vers}-bin.tar.gz &&\
    tar -zxvf apache-maven-${maven_vers}-bin.tar.gz && \
    mv apache-maven-${maven_vers} maven

COPY ./mavenenv.sh /etc/profile.d/mavenenv.sh
RUN chmod +x /etc/profile.d/mavenenv.sh

ENV M2_HOME=/opt/maven
ENV PATH=${M2_HOME}/bin:${PATH}

RUN apt-get install -y net-tools iputils-ping

RUN mvn -version
USER ${user}

ENTRYPOINT ["/home/jenkins/entry.sh"]