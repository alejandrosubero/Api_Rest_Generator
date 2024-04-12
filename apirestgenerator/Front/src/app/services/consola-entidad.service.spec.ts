import { TestBed } from '@angular/core/testing';

import { ConsolaEntidadService } from './consola-entidad.service';

describe('ConsolaEntidadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConsolaEntidadService = TestBed.get(ConsolaEntidadService);
    expect(service).toBeTruthy();
  });
});
