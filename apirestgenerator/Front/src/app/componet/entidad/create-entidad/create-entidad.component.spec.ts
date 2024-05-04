import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEntidadComponent } from './create-entidad.component';

describe('CreateEntidadComponent', () => {
  let component: CreateEntidadComponent;
  let fixture: ComponentFixture<CreateEntidadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateEntidadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateEntidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
