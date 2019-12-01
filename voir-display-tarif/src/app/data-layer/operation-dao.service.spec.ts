import { TestBed } from '@angular/core/testing';

import { OperationDaoService } from './operation-dao.service';

describe('OperationDaoService', () => {
  let service: OperationDaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OperationDaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
