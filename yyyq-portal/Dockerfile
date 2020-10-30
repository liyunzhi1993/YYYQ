FROM java:8-jre
MAINTAINER liyunzhi <liyunzhi1993@gmail.com>
RUN echo "Asia/Shanghai" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
ADD ./target/yyyq-portal-1.0.0.jar /app/yyyq-portal.jar
CMD ["java", "-Xmx200m", "-jar", "/app/yyyq-portal.jar", "--spring.profiles.active=${ENVIRONMENT}"]

EXPOSE 1011