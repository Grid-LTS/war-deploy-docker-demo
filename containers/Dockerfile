# Base image: Wildfly 12 with base-jdk:8
FROM jboss/wildfly

USER root

RUN  yum -y install wget

RUN yum update -y
RUN yum install -y curl net-tools iputils

COPY ./startup.sh /opt/jboss/startup.sh
RUN chown jboss:jboss /opt/jboss/startup.sh
RUN chmod +x /opt/jboss/startup.sh

USER jboss

# Add management user with password
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

ENTRYPOINT ["/opt/jboss/startup.sh"]