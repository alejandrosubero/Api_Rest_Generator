import { TestBed } from '@angular/core/testing';

import { HttpCommunService } from './http-commun.service';

describe('HttpCommunService', () => {
  let service: HttpCommunService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpCommunService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
