import { TestBed } from '@angular/core/testing';

import { MonitoringStationService } from './monitoring-station.service';

describe('MonitoringStationService', () => {
  let service: MonitoringStationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MonitoringStationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
