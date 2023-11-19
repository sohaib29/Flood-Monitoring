import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MonitoringStation, MonitoringStationReadings} from "../../Interfaces/MonitoringStations";

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrl: './station.component.scss'
})
export class StationComponent implements OnInit {
  stationData: any;
  readings!: MonitoringStationReadings;
  station!: MonitoringStation;
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
    console.log(this.readings,this.station);
  }
}
