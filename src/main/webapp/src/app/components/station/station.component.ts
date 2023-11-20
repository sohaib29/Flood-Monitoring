import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MonitoringStation, MonitoringStationReadings, Readings} from "../../Interfaces/MonitoringStations";

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrl: './station.component.scss'
})
export class StationComponent implements OnInit {
  stationData: any;
  readings!: MonitoringStationReadings;
  station!: MonitoringStation;
  readingMap: { [key: string]: Readings } = {};
  label = "Stage Level"

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.stationData = this.route?.snapshot?.data['stationResolver'];
    if (this.stationData != null) {
      this.setMonitoringStationReadings$();
    }
  }

  private setMonitoringStationReadings$() {
    this.readings = this.stationData['reading'];
    this.station = this.stationData['station'];
    this.readingMap = this.processObject(this.readings);
  }

  private processObject(inputObject: any): any {
    let resultObject = {};
    let hasNullField = false;

    for (let key in inputObject) {
      if (inputObject.hasOwnProperty(key)) {
        if (inputObject[key] === null) {
          hasNullField = true;
        } else {
          // @ts-ignore
          resultObject[key] = inputObject[key];
        }
      }
    }

    return hasNullField ? resultObject : null;
  }

}
