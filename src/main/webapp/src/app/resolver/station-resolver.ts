import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {MonitoringStationService} from "../services/monitoring-station.service";
import {MonitoringStationReadings} from "../Interfaces/MonitoringStations";
import {combineLatest, map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StationResolver implements Resolve<object> {

  constructor(private monitoringStationService: MonitoringStationService) {
  }

  resolve(route: ActivatedRouteSnapshot): any {
    const id = route.params['id'];
    const readings = this.monitoringStationService.getMonitoringStationReadings(id);
    const station = this.monitoringStationService.getMonitoringStation(id);

    return combineLatest([readings,station]).pipe(
      map(([reading, station]) => {
        return { reading, station };
      })
    );
  }

}
