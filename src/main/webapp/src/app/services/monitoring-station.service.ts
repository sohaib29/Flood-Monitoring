import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MonitoringStations} from "../Interfaces/MonitoringStations";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MonitoringStationService {

  constructor(private http: HttpClient) {
  }

  getMonitoringStations(): Observable<MonitoringStations[]> {
    console.log('Hello')
    const url = 'http://localhost:8080/stations';
    return this.http.get<MonitoringStations[]>(url);
  }
}
