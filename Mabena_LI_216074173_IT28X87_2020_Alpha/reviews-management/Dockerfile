FROM node:alpine

WORKDIR /app

COPY package.json ./

RUN npm install --production


COPY ./ ./

EXPOSE 4002


CMD ["npm", "start"]