FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17
WORKDIR /opt/jboss/wildfly
RUN cp standalone/configuration/standalone.xml standalone/configuration/standalone.xml.old
COPY standalone.xml standalone/configuration/

RUN mkdir webApp
WORKDIR /opt/jboss/wildfly/webApp
ADD target/ROOT.war .
RUN jar xvf ROOT.war
RUN rm ROOT.war
ADD whoami.jsp .

ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]