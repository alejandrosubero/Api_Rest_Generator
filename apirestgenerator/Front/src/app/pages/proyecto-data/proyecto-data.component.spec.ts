import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectoDataComponent } from './proyecto-data.component';

describe('ProyectoDataComponent', () => {
  let component: ProyectoDataComponent;
  let fixture: ComponentFixture<ProyectoDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectoDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProyectoDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
