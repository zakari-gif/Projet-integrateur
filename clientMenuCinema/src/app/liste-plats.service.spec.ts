import { TestBed } from '@angular/core/testing';

import { ListePlatsService } from './liste-plats.service';

describe('ListePlatsService', () => {
  let service: ListePlatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListePlatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
