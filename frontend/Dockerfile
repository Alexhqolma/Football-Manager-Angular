### STAGE 1: Build ###
FROM node:18.14-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY /docker/nginx.conf /etc/nginx/nginx.conf
COPY /docker/run.sh /run.sh
COPY --from=build /usr/src/app/dist/frontend /usr/share/nginx/html
CMD ["sh", "/run.sh"]
