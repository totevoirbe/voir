import { TestBed } from '@angular/core/testing';

import { DatalayerCommonService } from './datalayer-common.service';

describe('DatalayerCommonService', () => {
  let service: DatalayerCommonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatalayerCommonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
