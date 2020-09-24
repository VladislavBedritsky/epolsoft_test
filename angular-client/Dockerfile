FROM node:12.7-alpine
USER root
COPY . app/
WORKDIR /app
RUN npm install
RUN npm install @angular/cli -g
