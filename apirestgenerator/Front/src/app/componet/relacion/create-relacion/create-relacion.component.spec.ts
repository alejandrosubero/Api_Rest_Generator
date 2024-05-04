import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRelacionComponent } from './create-relacion.component';

describe('CreateRelacionComponent', () => {
  let component: CreateRelacionComponent;
  let fixture: ComponentFixture<CreateRelacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateRelacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRelacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
