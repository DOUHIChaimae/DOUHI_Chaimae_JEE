import { TestBed } from '@angular/core/testing';

import { TockenStorageService } from './tocken-storage.service';

describe('TockenStorageService', () => {
  let service: TockenStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TockenStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
