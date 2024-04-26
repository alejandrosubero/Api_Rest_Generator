import { TestBed } from '@angular/core/testing';

import { DevUserService } from './dev-user.service';

describe('DevUserService', () => {
  let service: DevUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DevUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
