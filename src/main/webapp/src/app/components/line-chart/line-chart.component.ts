import {Component, Input, OnInit} from '@angular/core';
import {Chart} from "chart.js";

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrl: './line-chart.component.scss'
})
export class LineChartComponent implements OnInit {
  public chart: any;
  @Input() dateTime!: string[];
  @Input() value!: number[];
  @Input() label!: string;

  ngOnInit(): void {
    console.log(this.label)
    this.createChart();
  }

  createChart() {

    this.chart = new Chart(this.label, {
      type: 'line',
      data: {
        labels: this.dateTime,
        datasets: [
          {
            label: this.label,
            data: this.value,
            backgroundColor: 'blue'
          }
        ]
      },
      options: {
        aspectRatio: 2.5
      }

    });
  }


}
