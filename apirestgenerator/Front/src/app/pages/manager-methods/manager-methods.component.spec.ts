import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerMethodsComponent } from './manager-methods.component';

describe('ManagerMethodsComponent', () => {
  let component: ManagerMethodsComponent;
  let fixture: ComponentFixture<ManagerMethodsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagerMethodsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerMethodsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
