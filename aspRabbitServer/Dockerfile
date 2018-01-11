FROM microsoft/dotnet:2.0-sdk
#FROM microsoft/dotnet:2.0-runtime
WORKDIR /app
copy ./aspRabbitServer/ ./
#RUN dotnet restore
#RUN dotnet publish -c Release -o out
EXPOSE 5672
EXPOSE 5671
EXPOSE 4369
#FROM microsoft/dotnet:2.0-runtime 
#WORKDIR /app
#COPY --from=build-env /app/out ./ 
#ENTRYPOINT ["dotnet","./out/RabbitServer.dll"]
CMD dotnet run
#ENTRYPOINT ["tail", "-f", "/dev/null"]
