import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MonitoringStation, MonitoringStationReadings, MonitoringStations} from "../Interfaces/MonitoringStations";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MonitoringStationService {

  constructor(private http: HttpClient) {
  }

  getMonitoringStations(): Observable<MonitoringStations[]> {
    const url = 'http://localhost:8080/stations';
    return this.http.get<MonitoringStations[]>(url);
  }

  getMonitoringStation(id: string): Observable<MonitoringStation> {
    const url = `http://localhost:8080/stations/${id}`;
    return this.http.get<MonitoringStation>(url);
  }

  getMonitoringStationReadings(id: string | undefined): Observable<MonitoringStationReadings> {
    const url = `http://localhost:8080/stations/${id}/readings`;
    console.log(url);
    return this.http.get<MonitoringStationReadings>(url);
  }
}
