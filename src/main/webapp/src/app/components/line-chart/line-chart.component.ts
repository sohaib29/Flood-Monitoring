import {Component, Input, OnInit} from '@angular/core';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrl: './line-chart.component.scss'
})
export class LineChartComponent implements OnInit {
  public chart: any;
  @Input() dateTime: any;
  @Input() value: any;
  @Input() label: any;
  ngOnInit(): void {
    this.createChart();
  }


  createChart(){

    this.chart = new Chart("MyChart", {
      type: 'line',

      data: {
        labels: this.dateTime?.reverse(),
        datasets: [
          {
            label: this.label,
            data: this.value?.reverse(),
            backgroundColor: 'blue'
          }
        ]
      },
      options: {
        aspectRatio:2.5
      }

    });
  }


}
