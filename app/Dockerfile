FROM gradle:8.5-correto-20

WORKDIR /app

COPY /app .

RUN gradle installDist

CMD ./build/install/app/bin/app
