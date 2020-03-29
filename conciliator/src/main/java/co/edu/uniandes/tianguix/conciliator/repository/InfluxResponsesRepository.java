package co.edu.uniandes.tianguix.conciliator.repository;

import co.edu.uniandes.tianguix.conciliator.model.Response;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfluxResponsesRepository implements ResponsesRepository {

    @Value("${influx.db.host}") private String databaseHost;
    @Value("${influx.db.username}") private String databaseUsername;
    @Value("${influx.db.password}") private String databasePassword;
    @Value("${influx.db.database}") private String database;

    @Override
    public void save(Response response){
        var influxDB = connectToDB();
        var point = Point.measurement("responses")
            .addField("response", response.getType().name())
            .build();

        influxDB.setDatabase(database);
        influxDB.write(point);
        influxDB.disableBatch();
        influxDB.close();
    }

    private InfluxDB connectToDB(){
        return InfluxDBFactory.connect(databaseHost, databaseUsername, databasePassword);
    }
}
