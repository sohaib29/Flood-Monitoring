import {Component, OnInit} from '@angular/core';
import {MonitoringStations, status} from "../../Interfaces/MonitoringStations";
import {Observable} from "rxjs";
import {MonitoringStationService} from "../../services/monitoring-station.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss',
})

export class HomepageComponent implements OnInit {
  dataSource = new MatTableDataSource<MonitoringStations>();
  displayedColumns: string[] = ['label', 'id', 'town', 'river', 'dateOpened', 'status'];

  monitoringStations$: Observable<MonitoringStations[]> | undefined;

  constructor(private monitoringStationService: MonitoringStationService) {
    this.setMonitoringStations$();
  }

  ngOnInit(): void {
  }

  private setMonitoringStations$() {
    this.monitoringStations$ = this.monitoringStationService.getMonitoringStations();
    this.monitoringStations$.subscribe(
      (data) => {
        this.dataSource.data = data;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }
}
