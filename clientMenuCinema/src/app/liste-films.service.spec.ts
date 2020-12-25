import { TestBed } from '@angular/core/testing';

import { ListeFilmsService } from './liste-films.service';

describe('ListeFilmsService', () => {
  let service: ListeFilmsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListeFilmsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
