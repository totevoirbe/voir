import { TestBed } from '@angular/core/testing';

import { ReadingJsonFilesService } from './reading-json-files.service';

describe('ReadingJsonFilesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReadingJsonFilesService = TestBed.get(ReadingJsonFilesService);
    expect(service).toBeTruthy();
  });
});
