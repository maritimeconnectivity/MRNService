FROM openjdk:8-jre

ADD mcp-mrn-service-latest.war .

# Set locales for image to support UTF-8
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

CMD ["java", "-jar", "mcp-mrn-service-latest.war"]