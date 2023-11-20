import {AfterViewInit, ChangeDetectorRef, Component, ViewChild} from '@angular/core';
import {MonitoringStations} from "../../Interfaces/MonitoringStations";
import {MonitoringStationService} from "../../services/monitoring-station.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator"
import {Router} from "@angular/router";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss',
})


export class HomepageComponent implements AfterViewInit {
  dataSource = new MatTableDataSource<MonitoringStations>();
  displayedColumns: string[] = ['label', 'id', 'town', 'river', 'dateOpened', 'status'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.cdr.detectChanges();
  }

  constructor(private monitoringStationService: MonitoringStationService,
              private cdr: ChangeDetectorRef,
              private router: Router) {
    this.setMonitoringStations$();
  }

  onRowClick(row: any): void {
    this.router.navigate(['/station', row.id]);
  }

  private setMonitoringStations$() {
    this.monitoringStationService.getMonitoringStations().subscribe((data: MonitoringStations[]) => {
      this.dataSource.data = data;
    });
  }
}
